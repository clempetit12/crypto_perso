// import Footer from "./components/shared/Footer";
import Footer from "./components/shared/Footer";
import Navbar from "./components/shared/Navbar";
import { Outlet } from "react-router-dom";


function App() {



  return (
    <div className="App">
      <header>
        <Navbar />
      </header>
      <main className="container mt-5">
        <div class="row justify-content-md-center">
          <div class="col-8">
            <Outlet />
            </div>
        </div>
      </main>
      <footer>
          <Footer/>
      </footer>
    </div>
  );
}

export default App;
