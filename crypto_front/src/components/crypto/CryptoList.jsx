import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getAllCryptos } from "./cryptoSlice";
import CryptoDisplay from "./CryptoDisplay";
import CryptoChart from "./CryptoChart";

const CryptoList = () => {
  const dispatch = useDispatch();
  const cryptos = useSelector((state) => state.cryptos.cryptos);

  useEffect(() => {
    dispatch(getAllCryptos());
  }, []);

  return (
    <div className="row my-3">
      <div className="d-flex justify-content-center align-items-center">
        <div className="flex items-center justify-between flex-wrap">
          <CryptoChart />
        </div>
      </div>
      <div className="rounded text-gray-800 p-3 d-flex justify-content-center">
        {cryptos.length === 0 ? (
          <div className="flex flex-col items-center font-bold justify-center text-cyan-600 mt-8">
            <h3 className="mb-4 mt-5">
              <i className="bi bi-ban me-1"></i>Sorry, crypto not found!
            </h3>
          </div>
        ) : (
          <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 gy-4">
            {cryptos.map((crypto, id) => (
              <CryptoDisplay key={crypto.id} crypto={crypto} />
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default CryptoList;
