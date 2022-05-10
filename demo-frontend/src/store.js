import { configureStore } from '@reduxjs/toolkit';
import userReducer from './features/user/userSlice';
import loginErrorReducer from './features/error/loginErrorSlice';

const store = configureStore({
  reducer: {
    user: userReducer,
    loginError: loginErrorReducer,
  },
});

export default store;
