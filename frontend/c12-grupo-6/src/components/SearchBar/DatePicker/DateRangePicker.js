import React, { useState, useEffect } from "react";
import { DatePicker } from "antd";
import moment from "moment";

import "antd/dist/antd.css";
import css from "../SearchBar.module.css";

const { RangePicker } = DatePicker;

function DateRangePicker({onPickDates}) {

    const [dates, setDates] = useState({0: moment(), 1: moment()});
    const [hackValue, setHackValue] = useState(null);
    const [value, setValue] = useState(null);
    const [start, setStart] = useState();
    const [end, setEnd] = useState();

    useEffect(() => {
        setStart(dates[0] == null? null: dates[0].toDate());
        setEnd(dates[1] == null? null: dates[1].toDate());                            
    
    }, [dates]);

    useEffect(()=> {
        onPickDates({start, end});  
    }, [start, end]);

    const onOpenChange = (open) => {
        if (open) {
            setHackValue([null, null]);
            setDates([null, null]);
        } else {
            setHackValue(null);
        }
    };
    return (
        <RangePicker
            value={hackValue || value}
            onCalendarChange={(val) => setDates(val)}
            onChange={(val) => setValue(val)}
            onOpenChange={onOpenChange}
            bordered={false}
            className={css.datePicker}
        />
    );
}

export default DateRangePicker;