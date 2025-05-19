'use client';
import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function MarketPage() {
  const [quotes, setQuotes] = useState([]);

  useEffect(() => {
    // Aquí solo llamamos a tu backend, no directamente a Finnhub
    axios.get('http://localhost:8080/v1/api/market/quotes', {
      params: {
        symbols: ['AAPL', 'MSFT', 'GOOGL', 'AMZN', 'TSLA']
      }
    })
    .then(response => setQuotes(response.data))
    .catch(error => console.error('Error al obtener cotizaciones:', error));
  }, []);

  return (
    <div className="bg-gray-900 text-white min-h-screen">
      <div className="container mx-auto p-6">
        <h2 className="text-3xl font-bold text-center mb-6">📈 Cotizaciones en Tiempo Real</h2>

        <div className="overflow-x-auto bg-gray-800 p-6 rounded-lg shadow-lg">
          <table className="min-w-full">
            <thead>
              <tr>
                <th className="px-6 py-3 text-left font-semibold">Símbolo</th>
                <th className="px-6 py-3 text-left font-semibold">Actual</th>
                <th className="px-6 py-3 text-left font-semibold">Máximo</th>
                <th className="px-6 py-3 text-left font-semibold">Mínimo</th>
                <th className="px-6 py-3 text-left font-semibold">Apertura</th>
                <th className="px-6 py-3 text-left font-semibold">Cierre Ant.</th>
              </tr>
            </thead>
            <tbody>
              {quotes.map((item, index) => (
                <tr key={index} className="border-b border-gray-700 hover:bg-gray-700">
                  <td className="px-6 py-4 font-semibold">{item.symbol}</td>
                  <td className="px-6 py-4">${item.quote?.currentPrice?.toFixed(2)}</td>
                  <td className="px-6 py-4">${item.quote?.highPrice?.toFixed(2)}</td>
                  <td className="px-6 py-4">${item.quote?.lowPrice?.toFixed(2)}</td>
                  <td className="px-6 py-4">${item.quote?.openPrice?.toFixed(2)}</td>
                  <td className="px-6 py-4">${item.quote?.previousClosePrice?.toFixed(2)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
