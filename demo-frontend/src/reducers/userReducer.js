import { createReducer, createAction } from '@reduxjs/toolkit';

const setUser = createAction('SET_USER');

const userReducer = createReducer([], (builder) => {
  builder.addCase(setUser, (state, action) => {});
});

export default userReducer;
