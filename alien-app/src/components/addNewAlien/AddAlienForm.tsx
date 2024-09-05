import React, { useState } from 'react';
import { Button, TextField, Box, Typography, Select, InputLabel, FormControl, MenuItem } from '@mui/material';
import { useDispatch } from 'react-redux';
import { AppDispatch } from '../../store';
import { addAlienChiefCommander, addAlienCommander, addWarrior } from '../../aliensSlice';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface AddAlienFormProps {
  alienType: string;
}

const AddAlienForm: React.FC<AddAlienFormProps> = ({ alienType }) => {
  const dispatch = useDispatch<AppDispatch>();
  const [name, setName] = useState('');
  const [commanderId, setCommanderId] = useState<number | null>(null);
  const [weapon, setWeapon] = useState('');
  const [vehicle, setVehicle] = useState('');
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const newAlien = {
      id: 0,
      name,
      commanderId,
      commanderName: null,
      weapon: alienType === 'Warrior' ? weapon : null,
      vehicle: alienType !== 'Warrior' ? vehicle : null,
    };

    try {
      if (alienType === 'Chief') {
        await dispatch(addAlienChiefCommander(newAlien)).unwrap();
      } else if (alienType === 'Commander') {
        await dispatch(addAlienCommander(newAlien)).unwrap();
      } else if (alienType === 'Warrior') {
        await dispatch(addWarrior(newAlien)).unwrap();
      }
      setError(null); // Clear any previous errors
    } catch (err) {
      setError(err as string);
      toast.error(err as string); // Display error in a toast notification
    }
  };

  const getVehicleOptions = () => {
    if (alienType === 'Chief') {
      return ['MERKAVA_TANK', 'BIRD_SCOOTER', 'EGGED_BUS'];
    } else if (alienType === 'Commander') {
      return ['MERKAVA_TANK', 'BIRD_SCOOTER'];
    }
    return [];
  };

  const getWeaponOptions = () => {
    if (alienType === 'Warrior') {
      return ['WATER_GUN', 'PEPPER_SPRAY', 'CHOPSTICKS'];
    }
    return [];
  };

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
      <Typography variant="h6">Adding a new {alienType}</Typography>

      {error && <Typography color="error">{error}</Typography>}

      <TextField
        label="Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        required
      />
      {alienType !== 'Chief' && (
        <TextField
          label="Commander ID"
          type="number"
          value={commanderId || ''}
          onChange={(e) => setCommanderId(Number(e.target.value))}
        />
      )}
      {alienType === 'Warrior' && (
        <FormControl>
          <InputLabel>Weapon</InputLabel>
          <Select
            value={weapon}
            onChange={(e) => setWeapon(e.target.value as string)}
          >
            {getWeaponOptions().map((option) => (
              <MenuItem key={option} value={option}>
                {option}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      )}
      {alienType !== 'Warrior' && (
        <FormControl>
          <InputLabel>Vehicle</InputLabel>
          <Select
            value={vehicle}
            onChange={(e) => setVehicle(e.target.value as string)}
          >
            {getVehicleOptions().map((option) => (
              <MenuItem key={option} value={option}>
                {option}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      )}
      <Button type="submit" variant="contained" color="primary">
        Submit
      </Button>
      <ToastContainer />
    </Box>
  );
};

export default AddAlienForm;