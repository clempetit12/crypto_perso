import { logOutAction } from "../trader/traderSlice";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import logo from './logo.png'


const Navbar = () => {
    const dispatch = useDispatch()
    const trader = useSelector(state => state.auth.trader)
  
    return ( 
        <div className="bg-gradient-to-r from-cyan-500 to-blue-500 px-6 py-4 shadow">
          <div className="flex flex-col container mx-auto md:flex-row md:items-center md:justify-between">
            <div className="flex justify-between items-center">
                <img className="size-24" src={logo} alt="Logo" />
                <Link className="text-white text-xl font-bold md:text-2xl" to={"/"}> LightWallet</Link>
            </div>
            <Link className="my-1 text-white-100 hover:text-lime-500 md:mx-4 md:my-0 me-5" to={"/sign"}>
              <i className="bi bi-person-fill personIcon"></i>
            </Link>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
              {trader ? (
                  <button className="btn btn-secondary ms-auto" onClick={() => dispatch(logOutAction())}>Sign out</button>
                ) : (
                  <Link className="btn btn-primary ms-auto" to={"/sign"} >Sign In</Link>
              )}
            </div>
          </div>
        </div>
     );
}
 
export default Navbar;