// import React from "react";
// 
// const CryptoDisplay = ({ crypto }) => {
//   if (!crypto) {
//     return <div>Loading...</div>;
//   }
// 
//   return (
//     <div className="flex items-center justify-between rounded-lg bg-white shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700 w-full">
//       <div className="px-6 py-3 border-b-2 border-neutral-100 dark:border-neutral-600 dark:text-neutral-50 font-bold">
//         {crypto.symbol}
//       </div>
//       <div className="p-6">
//         {crypto.name}
//       </div>
//       <div className="p-6 text-green-500">
//         + {crypto.performance}%
//       </div>
//       <div className="p-6">
//         $7.6
//       </div>
//     </div>
//   );
// }
// 
// export default CryptoDisplay;
import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { getPerformanceByDate } from "./cryptoSlice";

const CryptoDisplay = ({ crypto }) => {
  const dispatch = useDispatch();
  const [selectedPerformanceData, setselectedPerformanceData] = useState(null); 

  useEffect(() => {
  
    dispatch(getPerformanceByDate("2024-03-15")); 
  }, [dispatch]);


  const performanceFromRedux = useSelector(state => state.crypto.performance);
  console.log(performanceFromRedux);

  useEffect(() => {
    setselectedPerformanceData(performanceFromRedux);
  }, [performanceFromRedux]);

  return (
    <div className="flex items-center justify-between rounded-lg bg-white shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700 w-full">
      <div className="px-6 py-3 border-b-2 border-neutral-100 dark:border-neutral-600 dark:text-neutral-50 font-bold">
        {crypto.symbol}
      </div>
      <div className="p-6">
        {crypto.name}
      </div>
      <div className="p-6 text-green-500">
        {/* {crypto.performance !== null ? `+ ${selectedPerformanceData}%` : 'Loading...'}  */}
      </div>
      <div className="p-6">
        $7.6
      </div>
    </div>
  );
}

export default CryptoDisplay;
