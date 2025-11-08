import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [weather, setWeather] = useState([]);
  const [newCity, setNewCity] = useState('');

  const fetchWeather = async (city) => {
    try {
      if (city) {
        await axios.post(`/api/weather/${city}`);
      }
      const res = await axios.get('/api/weather');
      setWeather(res.data);
    } catch (error) {
      console.error('Error fetching weather:', error);
    }
  };

  useEffect(() => {
    fetchWeather('London');
    const interval = setInterval(() => fetchWeather(), 60000);
    return () => clearInterval(interval);
  }, []);

  const handleAddCity = () => {
    if (newCity.trim() !== '') {
      fetchWeather(newCity);
      setNewCity('');
    }
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial' }}>
      <h1>Weather Dashboard</h1>
      <div style={{ marginBottom: '10px' }}>
        <input
          type="text"
          value={newCity}
          onChange={(e) => setNewCity(e.target.value)}
          placeholder="Enter city"
        />
        <button onClick={handleAddCity}>Add City</button>
      </div>
      {weather.length === 0 && <p>No weather data yet</p>}
      {weather.map((w) => (
        <div key={w.id} style={{
          border: '1px solid #ccc',
          padding: '10px',
          marginBottom: '10px',
          borderRadius: '8px',
          maxWidth: '300px'
        }}>
          <h2>{w.city}</h2>
          <p>{w.description}</p>
          {w.icon && <img src={w.icon} alt={w.description} />}
          <p>Temperature: {w.temperature}°C</p>
          <p>Wind: {w.windSpeed} km/h</p>
          <p>Humidity: {w.humidity}%</p>
          <p>Recorded at: {new Date(w.createdAt).toLocaleString()}</p>
        </div>
      ))}
    </div>
  );
}

export default App;