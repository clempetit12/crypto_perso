import { configureStore } from '@reduxjs/toolkit'
import traderSlice from './components/trader/traderSlice'
import cryptoSlice from './components/crypto/cryptoSlice'
import walletSlice from './components/wallet/walletSlice'


const store = configureStore({
  reducer: {
    auth: traderSlice,
    cryptos: cryptoSlice,
    wallets: walletSlice
  }
})

export default store