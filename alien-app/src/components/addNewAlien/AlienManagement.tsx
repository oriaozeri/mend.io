import React, { useState } from 'react';
import { Button, Box, MenuItem, Select, FormControl, InputLabel } from '@mui/material';
import AddAlienForm from './AddAlienForm';
import { SelectChangeEvent } from '@mui/material';

const AlienManagement: React.FC = () => {
  const [showForm, setShowForm] = useState(false);
  const [alienType, setAlienType] = useState<string>('Warrior');

  const handleButtonClick = () => {
    setShowForm((prev) => !prev); 
  };

  const handleAlienTypeChange = (event: SelectChangeEvent<string>) => {
    setAlienType(event.target.value as string);
  };

  return (
    <Box sx={{ textAlign: 'center', marginTop: 4 }}>
      <Button
        variant="contained"
        color="primary"
        onClick={handleButtonClick}
      >
        {showForm ? 'Hide Form' : 'Add New Alien'}
      </Button>

      {showForm && (
        <Box sx={{ marginTop: 4 }}>
          <FormControl variant="outlined" sx={{ minWidth: 200, marginBottom: 2 }}>
            <InputLabel id="alien-type-label">Alien Type</InputLabel>
            <Select
              labelId="alien-type-label"
              id="alien-type-select"
              value={alienType}
              onChange={handleAlienTypeChange}
              label="Alien Type"
            >
              <MenuItem value="Warrior">Warrior</MenuItem>
              <MenuItem value="Commander">Commander</MenuItem>
              <MenuItem value="Chief">Chief</MenuItem>
            </Select>
          </FormControl>

          <AddAlienForm alienType={alienType} />
        </Box>
      )}
    </Box>
  );
};

export default AlienManagement;
