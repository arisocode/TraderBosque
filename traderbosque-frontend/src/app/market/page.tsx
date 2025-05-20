'use client';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { TableSymbols } from '../components/Table';

export default function MarketPage() {
  const [quotes, setQuotes] = useState([]);

  useEffect(() => {
    // Aquí solo llamamos a tu backend, no directamente a Finnhub
    axios.get('http://localhost:8080/v1/api/market/quotes')
      .then(response => setQuotes(response.data))
      .catch(error => console.error('Error al obtener cotizaciones:', error));

  }, []);

  return (
    <div className="bg-gray-900 text-white min-h-screen">
      <div className="container mx-auto p-6">
        <h2 className="text-3xl font-bold text-center mb-6">📈 Cotizaciones en Tiempo Real</h2>

        <div className="overflow-x-auto bg-gray-800 p-6 rounded-lg shadow-lg">
          <TableSymbols data={quotes} />
        </div>
      </div>
    </div>
  );
}
