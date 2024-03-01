import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";

const ProtectedRoute = (props) => {
    const trader = useSelector(state => state.auth.trader)
    
    if(trader) {
        return(
            <>
            {props.children}
            </>
        )
    } else {
        return <Navigate to={"/sign"}></Navigate>
    }
}
 
export default ProtectedRoute;