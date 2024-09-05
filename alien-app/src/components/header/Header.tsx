import React from 'react';
import { AppBar, Toolbar, Typography } from '@mui/material';
import Alien from '../../assets/alien.svg';
import './Header.css';

interface HeaderProps {
  title: string;
}

const AlienImage: React.FC = () => {
  return (
    <div className="alien-image-container">
      <img src={Alien} alt="Alien" className="alien-image" />
    </div>
  );
};

const Header: React.FC<HeaderProps> = ({ title }) => {
  return (
    <AppBar position="static">
      <Toolbar>
        <AlienImage />
        <Typography variant="h6" style={{ flexGrow: 1 }}>
          {title}
        </Typography>
      </Toolbar>
    </AppBar>
  );
};

export default Header;