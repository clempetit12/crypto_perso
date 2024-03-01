import React, { useState, useEffect } from "react";
import CanvasJSReact from '@canvasjs/react-stockcharts';
import { useDispatch, useSelector } from "react-redux";
import { getMarketDataForOneCrypto } from "../marketData/marketDataSlice";

var CanvasJSStockChart = CanvasJSReact.CanvasJSStockChart;

const CryptoChart = ({ cryptoId }) => {
  const [dataPoints, setDataPoints] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const dispatch = useDispatch();
  const datas = useSelector((state) => state.marketDatas.marketDatas);

  useEffect(() => {
    dispatch(getMarketDataForOneCrypto("65e1950288942f7a97757faf")); 
  }, [dispatch, cryptoId]);

  useEffect(() => {
    if (datas.length > 0) {
      const formattedData = datas.map(item => ({
        x: new Date(item.tradingTime),
        y: parseFloat(item.cryptoValue) 
      }));
      setDataPoints(formattedData);
      setIsLoaded(true);
    }
  }, [datas]);

  const options = {
    theme: "light2",
    title: {
      text: "Coût de la crypto-monnaie sur un mois"
    },
    data: [{
      type: "line",
      dataPoints: dataPoints
    }]
  };

  const containerProps = {
    width: "100%",
    height: "400px", 
    margin: "auto"
  };

  return (
    <div>
      <div>
        {isLoaded &&
          <CanvasJSStockChart containerProps={containerProps} options={options} />
        }
      </div>
    </div>
  );
}

export default CryptoChart;
