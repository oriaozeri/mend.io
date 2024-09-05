import React from 'react';
import { Provider } from 'react-redux';
import { store } from './store';
import AlienList from './components/alienList/AlienList';
import Header from './components/header/Header';

const App: React.FC = () => {
  return (
    <Provider store={store}>
      <Header title="The Alien Army" />
      <AlienList />
    </Provider>
  );
};

export default App;