import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import SignForm from "./components/trader/SignForm"
import ProtectedRoute from "./components/shared/ProtectedRoute";
import CryptoList from "./components/crypto/CryptoList"




const router = createBrowserRouter([
    {
        path :"/",
        element : <App />,
        children: [
            {
                path: "/",
                element: <CryptoList />
            },
            // {
            //     path :"/wallet",
            //     element : <ProtectedRoute><Wallet /></ProtectedRoute>
            // },
            {
                path: "/sign",
                element: <SignForm />
            }
        ]
    },
])

export default router