import { configureStore } from '@reduxjs/toolkit'
import traderSlice from './components/trader/traderSlice'
import cryptoSlice from './components/crypto/cryptoSlice'
import walletSlice from './components/wallet/walletSlice'
import marketDataSlice from './components/marketData/marketDataSlice'


const store = configureStore({
  reducer: {
    auth: traderSlice,
    cryptos: cryptoSlice,
    wallets: walletSlice,
    marketDatas : marketDataSlice
  }
})

export default store