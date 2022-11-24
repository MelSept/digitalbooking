package com.digitalbooking.apilodgings.service.reservation;

import com.digitalbooking.apilodgings.dto.reservation.CreateReservationDTO;
import com.digitalbooking.apilodgings.dto.reservation.ReservationDTO;
import com.digitalbooking.apilodgings.entity.Product;
import com.digitalbooking.apilodgings.entity.Reservation;
import com.digitalbooking.apilodgings.entity.User;
import com.digitalbooking.apilodgings.enums.EStatus;
import com.digitalbooking.apilodgings.exception.BadRequestException;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.repository.IProductRepository;
import com.digitalbooking.apilodgings.repository.IReservationRepository;
import com.digitalbooking.apilodgings.repository.IUserRepository;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.ResponseError;
import com.digitalbooking.apilodgings.utility.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("ReservationServiceImpl")
public class ReservationServiceImpl implements IReservationService {


    private final IReservationRepository reservationRepository;
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;
    private final ObjectMapper mapper;


    @Autowired
    public ReservationServiceImpl(@Qualifier("IReservationRepository") IReservationRepository reservationRepository,
                                  @Qualifier("IProductRepository") IProductRepository productRepository,
                                  @Qualifier("IUserRepository") IUserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.mapper = new ObjectMapper();
        setupMapper();
    }


    private void setupMapper() {
        this.mapper.registerModule(new JavaTimeModule());
    }


    @Override
    public ReservationDTO createReservation(CreateReservationDTO reservationDTO) throws NotFoundException, BadRequestException {

        Integer productId = reservationDTO.getProductId();
        Integer userId = reservationDTO.getUserId();

        Date checkIn = DateUtils.asDate(reservationDTO.getCheckIn());
        Date checkOut = DateUtils.asDate(reservationDTO.getCheckOut());

        Product productFound = productRepository.findByIdAndDeletedIsFalse(productId).orElse(null);
        User userFound = userRepository.findById(userId).orElse(null);
        Reservation reservationFound =
                reservationRepository.findByProduct_IdAndCheckOutIsBetween(productId, checkIn, checkOut);

        if (productFound == null) {
            Response response = new Response(String.format("Product with id: %s not found", productId));
            throw new NotFoundException(response);
        }
        if (userFound == null) {
            Response response = new Response(String.format("User with id: %s not found", userId));
            throw new NotFoundException(response);
        }

        if (checkOut.before(checkIn)){
            ResponseError response = new ResponseError("The 'CheckOut' cannot precede the 'CheckIn'");
            throw new BadRequestException(response);
        }

        if (checkIn.after(checkOut)){
            ResponseError response = new ResponseError("The 'CheckIn' cannot be later the 'CheckOut'");
            throw new BadRequestException(response);
        }

        if (reservationFound != null)
        {
            ResponseError response = new ResponseError("Reservation cannot be created");
            response.addHint("Reservation Exist");
            throw new BadRequestException(response);
        }

        Reservation reservationToSave = new Reservation();
        reservationToSave.setCheckIn(checkIn);
        reservationToSave.setCheckOut(checkOut);
        reservationToSave.setStartTime(reservationDTO.getStartTime());
        reservationToSave.setStatus(EStatus.DONE);
        reservationToSave.setUser(userFound);
        reservationToSave.setProduct(productFound);

        Reservation reservationSaved = reservationRepository.save(reservationToSave);

        return mapper.convertValue(reservationSaved, ReservationDTO.class);
    }

    @Override
    public List<ReservationDTO> findAllReservationsByProductId(Integer productId) {

        List<Reservation> reservationsFound = reservationRepository.findAllByProductId(productId);
        List<ReservationDTO> reservationsDTOFound = new ArrayList<>();

        for (Reservation reservation : reservationsFound) {

            ReservationDTO reservationDTO = mapper.convertValue(reservation, ReservationDTO.class);
            reservationsDTOFound.add(reservationDTO);
        }

        return reservationsDTOFound;
    }
}
