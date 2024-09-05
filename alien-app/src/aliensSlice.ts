import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

interface Alien {
  id: number;
  name: string;
  commanderId: number | null;
  commanderName: String | null;
  weapon: string | null;
  vehicle: string | null;
}

interface AliensState {
  aliens: Alien[];
  status: 'idle' | 'loading' | 'succeeded' | 'failed';
}

const initialState: AliensState = {
  aliens: [],
  status: 'idle',
};

export const fetchAliens = createAsyncThunk('aliens/fetchAliens', async () => {
  const response = await fetch('/api/aliens');
  return (await response.json()) as Alien[];
});

export const addAlienChiefCommander = createAsyncThunk(
  '/aliens/addAlienChiefCommander',
  async (newAlien: Alien, { rejectWithValue }) => {
    try {
      const response = await fetch('/api/aliens/addAlienChiefCommander', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newAlien),
      });

      if (!response.ok) {
        if (response.status === 404) {
          throw new Error('Resource not found');
        }
        throw new Error('Network response was not ok');
      }
      if (response.status === 201) {
        return newAlien;
      }


      return (await response.json()) as Alien;
    } catch (error) {
      if (error instanceof Error) {
        return rejectWithValue(error.message);
      } else {
        return rejectWithValue('An unknown error occurred');
      }
    }
  }
);
export const addAlienCommander = createAsyncThunk(
  '/aliens/addAlienCommander',
  async (newAlien: Alien, { rejectWithValue }) => {
    try {
      const response = await fetch('/api/aliens/addAlienCommander', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newAlien),
      });

      return await handleResponse(response);
    } catch (error) {
      if (error instanceof Error) {
        return rejectWithValue(error.message);
      } else {
        return rejectWithValue('An unknown error occurred');
      }
    }
  }
);
  
export const addWarrior = createAsyncThunk(
  '/aliens/addWarrior',
  async (newAlien: Alien, { rejectWithValue }) => {
    try {
      const response = await fetch('/api/aliens/addWarrior', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newAlien),
      });

      return await handleResponse(response);
    } catch (error) {
      if (error instanceof Error) {
        return rejectWithValue(error.message);
      } else {
        return rejectWithValue('An unknown error occurred');
      }
    }
  }
);
    
  const handleResponse = async (response: Response) => {
    if (!response.ok) {
      if (response.status === 400) {
        const errorResponse = await response.json();
        throw new Error(errorResponse.name);
      }
      throw new Error('Network response was not ok');
    }
    return (await response.json()) as Alien;
  };
  
const aliensSlice = createSlice({
  name: 'aliens',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchAliens.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchAliens.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.aliens = action.payload;
      })
      .addCase(fetchAliens.rejected, (state) => {
        state.status = 'failed';
      })
      .addCase(addAlienChiefCommander.fulfilled, (state, action) => {
        state.aliens.push(action.payload);
      });
  },
});

export default aliensSlice.reducer;
