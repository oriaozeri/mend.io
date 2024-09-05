import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchAliens } from '../../aliensSlice';
import { RootState, AppDispatch } from '../../store';
import AlienTable from '../alienList/AlienTable';
import AlienManagement from '../addNewAlien/AlienManagement';

const AlienList: React.FC = () => {
  const dispatch: AppDispatch = useDispatch();
  const { aliens, status } = useSelector((state: RootState) => state.aliens);

  useEffect(() => {
    const socket = new WebSocket('ws://localhost:8080/ws/aliens');

    socket.onopen = () => {
      console.log('WebSocket connection established');
    };

    socket.onmessage = (event) => {
      console.log('Message from server:', event.data);
      dispatch(fetchAliens());
    };

    socket.onerror = (error) => {
      console.error('WebSocket error', error);
    };

    socket.onclose = () => {
      console.log('WebSocket connection closed');
    };

    return () => {
      socket.close();
    };
  }, [dispatch]);

  useEffect(() => {
    dispatch(fetchAliens());
  }, [dispatch]);

  return (
    <>  
      <div style={{ padding: '20px' }}>
        <AlienManagement/>
        {status === 'loading' && <p>Loading...</p>}
        {status === 'failed' && <p>Failed to load aliens.</p>}
        {status === 'succeeded' && <AlienTable aliens={aliens} /> }
      </div>
    </>
  );
};

export default AlienList;