import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";
import Router from "./components/Route/Router";
import { UserProvider } from "./context/UserContext";

const App = () => {
  return (
    <div className="App">
      <UserProvider>
        <Header />
        <Router />
        <Footer />
      </UserProvider>
    </div>
  );
};

export default App;
