import React from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper ,Typography} from '@mui/material';

interface Alien {
  id: number;
  name: string;
  commanderId: number | null;
  commanderName: String | null;
  weapon: string | null;
  vehicle: string | null;
}

interface AlienTableProps {
  aliens: Alien[];
}

const AlienTable: React.FC<AlienTableProps> = ({ aliens }) => {
  if (aliens.length === 0) {
    return <Typography variant="h6" style={{ flexGrow: 1, marginBottom: '16px' , marginTop: '16px'}}>No aliens to display, Please add new one</Typography> 
  }

  return (
    <>
      <Typography variant="h6" style={{ flexGrow: 1, marginBottom: '16px' , marginTop: '16px'}}>Alien List</Typography>
      <TableContainer component={Paper}>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell>Name</TableCell>
            <TableCell>Commander ID</TableCell>
            <TableCell>Commander Name</TableCell>
            <TableCell>Weapon</TableCell>
            <TableCell>Vehicle</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {aliens.map((alien) => (
            <TableRow key={alien.id}>
              <TableCell>{alien.id}</TableCell>
              <TableCell>{alien.name}</TableCell>
              <TableCell>{alien.commanderId ?? ''}</TableCell>
              <TableCell>{alien.commanderName ?? ''}</TableCell>
              <TableCell>{alien.weapon ?? ''}</TableCell>
              <TableCell>{alien.vehicle ?? ''}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </>
  );
};

export default AlienTable;
