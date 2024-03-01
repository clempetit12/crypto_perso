
import React, { useEffect, useState } from "react";
import axios from "axios";
import { useSelector, useDispatch } from "react-redux";
import { getPerformanceByDate } from "./cryptoSlice";


const CryptoDisplay = ({ crypto }) => {
  const [performance, setPerformance] = useState(null);
  const [cryptoValue, setCryptoValue] = useState(null);
  const textColor = performance && parseFloat(performance) < 0 ? "text-red-500" : "text-green-500";
  const API_URL = "http://localhost:8080/crypto/";

  useEffect(() => {
    if (crypto) {
      const fetchPerformance = async () => {
        try {
          const response = await axios.get(`${API_URL}performance/2024-03-10/${crypto.id}`);
          const performanceValue = response.data;
          console.log("response axios " + response.data.performance);
          setPerformance(performanceValue);
        } catch (error) {
          console.error("Error fetching performance:", error);
        }
      };

      const fetchCryptoValue = async () => {
        try {
          const response = await axios.get(`${API_URL}price/2024-03-12/${crypto.id}`);
          const cryptoValue = Math.round(response.data);
          console.log(cryptoValue);
          console.log("response axios " + response.data.cryptoValue);
          setCryptoValue(cryptoValue);
        } catch (error) {
          console.error("Error fetching crypto value:", error);
        }
      };

      fetchPerformance();
      fetchCryptoValue();
    }
  }, [crypto]);

  if (!crypto) {
    return <div>Loading...</div>;
  }

  return (
    <div className="flex items-center justify-between rounded-lg bg-white shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700 w-full">
      <div className="px-6 py-3 border-b-2 border-neutral-100 dark:border-neutral-600 dark:text-neutral-50 font-bold">
        {crypto.symbol}
      </div>
      <div className="p-6">
        {crypto.name}
      </div>
      <div className={`p-6 ${textColor}`}>
        {performance !== null ? performance : "Loading..."}%
      </div>
      <div className="p-6">
        {cryptoValue !== null ? `$${cryptoValue}` : "Loading..."}
      </div>
    </div>
  );
};

export default CryptoDisplay;
