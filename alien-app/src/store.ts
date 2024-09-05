import { configureStore } from '@reduxjs/toolkit';
import aliensReducer from './aliensSlice';

export const store = configureStore({
  reducer: {
    aliens: aliensReducer,
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
