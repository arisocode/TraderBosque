'use client';

import axios from 'axios';
import { useRouter } from 'next/navigation';
import qs from 'qs';
import React, { useEffect, useState } from 'react';
import { StockChart } from './StockChart';
import { Quote, Symbols } from './Symbols';

export default function MarketPage() {
  const router = useRouter();
  const [quotes, setQuotes] = useState<Quote[]>([]);
  const [selectedSymbol, setSelectedSymbol] = useState('');
  type HistoricalDataItem = { time: string; price: number };
  const [historicalData, setHistoricalData] = React.useState<HistoricalDataItem[]>([]);

  const [orderType, setOrderType] = useState<'buy' | 'sell'>('buy');
  const [orderKind, setOrderKind] = useState<'market' | 'limit' | 'stop'>('market');
  const [quantity, setQuantity] = useState<number>(1);
  const [limitPrice, setLimitPrice] = useState<number | ''>('');
  const [orderStatus, setOrderStatus] = useState<string | null>(null);

  useEffect(() => {
    const symbols = ['AAPL', 'MSFT', 'TSLA', 'AMZN', 'GOOGL'];

    axios.get('http://localhost:8080/v1/api/market/quotes', {
      params: { symbols },
      paramsSerializer: params => qs.stringify(params, { arrayFormat: 'repeat' })
    })
    .then(response => {
      console.log('Datos recibidos:', response.data);
      setQuotes(response.data);
    })
    .catch(error => console.error('Error al obtener cotizaciones:', error));
  }, []);

  function simulateHistoricalData(currentPrice: number) {
    const data = [];
    let price = currentPrice;

    for (let i = 30; i >= 0; i--) {
      price = price + (Math.random() - 0.5); // variación ±0.5
      data.push({
        time: new Date(Date.now() - i * 60 * 1000).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
        price: parseFloat(price.toFixed(2)),
      });
    }
    return data;
  }

  useEffect(() => {
    if (!selectedSymbol) return;
    const quote = quotes.find(q => q.symbol === selectedSymbol);

    if (quote && quote.quote && typeof quote.quote.c === 'number') {
      const simulatedData = simulateHistoricalData(quote.quote.c);
      setHistoricalData(simulatedData);
    } else {
      setHistoricalData([]); // no hay datos para simular
    }
  }, [selectedSymbol, quotes]);

  const handleSubmitOrder = async (e: React.FormEvent) => {
    e.preventDefault();

    const selectedQuote = quotes.find(q => q.symbol === selectedSymbol);

    if (!selectedQuote || !selectedQuote.quote || typeof selectedQuote.quote.c !== 'number') {
      setOrderStatus('Precio actual no disponible para el símbolo seleccionado');
      return;
    }

    if (quantity <= 0) {
      setOrderStatus('Cantidad debe ser mayor que cero');
      return;
    }

    if ((orderKind === 'limit' || orderKind === 'stop') && (typeof limitPrice !== 'number' || limitPrice <= 0)) {
      setOrderStatus('Debe ingresar un precio límite válido');
      return;
    }

    const orderPayload = {
      symbol: selectedSymbol,
      side: orderType,
      type: orderKind,
      qty: quantity,
      time_in_force: "gtc",
      currentPrice: selectedQuote.quote.c,
      limitPrice: limitPrice,
    };

    try {
      setOrderStatus('Enviando orden...');
      
      const response = await axios.post('http://localhost:8080/v1/api/trading/order', orderPayload, { withCredentials: true });

      
      setOrderStatus('Orden enviada correctamente ');
      console.log('Respuesta orden:', response.data);

    } catch (error: any) {

      if (error.response && error.response.data) {
        const backendMessage = error.response.data.message;
        setOrderStatus(`Error: ${backendMessage}`);
        console.log('Error en la orden:', backendMessage);

      } else {
        setOrderStatus('Error desconocido al enviar la orden');
        console.error('Error inesperado:', error);
      }
    }
  };

  const handleLoadBalance = () => {
    router.push('/market/Funds');
  };

  return (
    <div className="bg-gray-900 text-white min-h-screen">
      <div className="container mx-auto p-6">
        <h2 className="text-3xl font-bold text-center mb-6">📈 Cotizaciones en Tiempo Real</h2>

        <div className="overflow-x-auto bg-gray-800 p-6 rounded-lg shadow-lg">
          <Symbols data={quotes} onSelectSymbol={setSelectedSymbol} />
        </div>

        {selectedSymbol && (
          <>
            <div className="mt-6 bg-gray-800 p-4 rounded-lg shadow">
              <h3 className="text-xl font-semibold mb-4">Historico de {selectedSymbol}</h3>
              <StockChart data={historicalData} />
            </div>

            {/* Botón para cargar saldo */}
            <div className="mt-6 flex justify-center">
              <button
                onClick={handleLoadBalance}
                className="bg-blue-600 hover:bg-blue-700 transition px-4 py-2 rounded text-white"
              >
                Cargar saldo
              </button>
            </div>

            {/* Formulario de orden */}
            <form onSubmit={handleSubmitOrder} className="mt-6 bg-gray-800 p-6 rounded-lg shadow max-w-md mx-auto">
              <h3 className="text-xl font-semibold mb-4">Realizar orden para {selectedSymbol}</h3>

              <label className="block mb-2">
                Tipo de orden:
                <select
                  value={orderType}
                  onChange={e => setOrderType(e.target.value as 'buy' | 'sell')}
                  className="w-full mt-1 p-2 rounded bg-gray-700 text-white"
                >
                  <option value="buy">Compra</option>
                  <option value="sell">Venta</option>
                </select>
              </label>

              <label className="block mb-2">
                Tipo:
                <select
                value={orderKind}
                onChange={e => setOrderKind(e.target.value as 'market' | 'limit' | 'stop')}
                className="w-full mt-1 p-2 rounded bg-gray-700 text-white"
                disabled={orderType === 'sell'} // Desactiva para ventas
              >
                <option value="market">Market (Precio actual)</option>
                {orderType === 'buy' && (
                  <>
                    <option value="limit">Limit (Precio límite)</option>
                    <option value="stop">Stop</option>
                  </>
                )}
              </select>
              </label>

              <label className="block mb-2">
                Cantidad:
                <input
                  type="number"
                  min="1"
                  value={quantity}
                  onChange={e => setQuantity(parseInt(e.target.value))}
                  className="w-full mt-1 p-2 rounded bg-gray-700 text-white"
                />
              </label>

              {(orderKind === 'limit' || orderKind === 'stop') && (
                <label className="block mb-4">
                  Precio límite:
                  <input
                    type="number"
                    min="0.01"
                    step="0.01"
                    value={limitPrice}
                    onChange={e => setLimitPrice(parseFloat(e.target.value))}
                    className="w-full mt-1 p-2 rounded bg-gray-700 text-white"
                  />
                </label>
              )}

              <button
                type="submit"
                className="bg-green-600 hover:bg-green-700 transition px-4 py-2 rounded text-white w-full"
              >
                Enviar orden
              </button>

              {orderStatus && <p className="mt-3 text-center">{orderStatus}</p>}
            </form>
          </>
        )}
      </div>
    </div>
  );
}
