import { useEffect, useRef, useState } from 'react';

interface Alien {
  id: number;
  name: string;
  commanderId: number | null;
  commanderName: String | null;
  weapon: string | null;
}

const useWebSocket = (url: string) => {
  const [aliens, setAliens] = useState<Alien[]>([]);
  const ws = useRef<WebSocket | null>(null);

  useEffect(() => {
    ws.current = new WebSocket(url);

    ws.current.onopen = () => {
      console.log('WebSocket connection opened');
    };

    ws.current.onmessage = (event) => {
      const newAlien = JSON.parse(event.data) as Alien;
      setAliens((prevAliens) => [...prevAliens, newAlien]);
    };

    ws.current.onclose = () => {
      console.log('WebSocket connection closed');
    };

    return () => {
      ws.current?.close();
    };
  }, [url]);

  const sendMessage = (message: string) => {
    ws.current?.send(message);
  };

  return { aliens, sendMessage };
};

export default useWebSocket;
