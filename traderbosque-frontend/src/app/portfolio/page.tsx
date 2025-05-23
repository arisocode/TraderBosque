'use client';

import {
  BarElement,
  CategoryScale,
  Chart as ChartJS,
  Legend,
  LinearScale,
  Title,
  Tooltip,
} from 'chart.js';
import { Bar } from "react-chartjs-2";
import Layout from "../components/accLayout";
import { usePortfolio } from "./hooks/usePortfolio";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const currencyFormatter = new Intl.NumberFormat("es-CO", {
  style: "currency",
  currency: "USD",
  minimumFractionDigits: 2,
});

const percentFormatter = (value: number) => `${value.toFixed(2)}%`;

export default function PortfolioPage() {
  const { portfolio, loading } = usePortfolio();

  if (loading) return <div className="text-gray-600 text-center mt-10">Cargando portafolio...</div>;
  if (!portfolio || portfolio.holdings.length === 0) {
    return <div className="text-gray-600 text-center mt-10">No hay datos disponibles del portafolio.</div>;
  }

  const chartData = {
    labels: portfolio.holdings.map((h) => h.symbol),
    datasets: [
      {
        label: "Ganancia/Pérdida ($)",
        data: portfolio.holdings.map((h) => h.gainLossAmount),
        backgroundColor: portfolio.holdings.map((h) =>
          h.gainLossAmount >= 0 ? "#16a34a" : "#dc2626"
        ),
      },
    ],
  };

  return (
    <Layout>
      <div className="bg-white min-h-screen text-gray-800 p-8 space-y-8">

        {/* Título y botón */}
        <div className="text-center space-y-2">
          <h1 className="text-4xl font-bold tracking-wide">Portafolio de Inversiones</h1>
          <button
            onClick={() => location.reload()}
            className="text-sm text-blue-600 hover:underline"
          >
            🔄 Actualizar portafolio
          </button>
        </div>

        {/* KPIs */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 text-center">
          <div className="bg-gray-100 rounded-xl p-6 shadow-md">
            <p className="text-gray-500 mb-1">Total Invertido</p>
            <p className="text-2xl font-bold">{currencyFormatter.format(portfolio.totalInvested)}</p>
          </div>
          <div className="bg-gray-100 rounded-xl p-6 shadow-md">
            <p className="text-gray-500 mb-1">Valor Actual</p>
            <p className="text-2xl font-bold">{currencyFormatter.format(portfolio.totalCurrentValue)}</p>
          </div>
          <div className="bg-gray-100 rounded-xl p-6 shadow-md">
            <p className="text-gray-500 mb-1">Ganancia / Pérdida</p>
            <p className={`text-2xl font-bold ${portfolio.totalGainLossAmount >= 0 ? 'text-green-600' : 'text-red-600'}`}>
              {currencyFormatter.format(portfolio.totalGainLossAmount)} ({percentFormatter(portfolio.totalGainLossPercentage)})
            </p>
          </div>
        </div>

        {/* Gráfico */}
        <div className="bg-gray-100 rounded-xl p-6 shadow-md">
          <h2 className="text-xl font-semibold mb-4">Rendimiento por Activo</h2>
          <div className="w-full h-[300px]">
            <Bar
              data={chartData}
              options={{ responsive: true, maintainAspectRatio: false }}
            />
          </div>
        </div>

        {/* Tabla */}
        <div className="bg-gray-100 rounded-xl p-6 shadow-md">
          <h2 className="text-xl font-semibold mb-4">Detalle de Holdings</h2>
          <div className="overflow-auto rounded-md">
            <table className="w-full text-sm text-left">
              <thead className="text-gray-500 border-b border-gray-300">
                <tr>
                  <th className="p-2">Símbolo</th>
                  <th className="p-2">Cantidad</th>
                  <th className="p-2">Precio Prom.</th>
                  <th className="p-2">Precio Actual</th>
                  <th className="p-2">Valor Actual</th>
                  <th className="p-2">Ganancia</th>
                </tr>
              </thead>
              <tbody>
                {portfolio.holdings.map((h, i) => (
                  <tr
                    key={i}
                    className={`border-b border-gray-200 ${i % 2 === 0 ? 'bg-gray-50' : 'bg-white'} hover:bg-gray-100`}
                  >
                    <td className="p-2">{h.symbol}</td>
                    <td className="p-2">{h.quantity}</td>
                    <td className="p-2">{currencyFormatter.format(h.avgPrice)}</td>
                    <td className="p-2">{currencyFormatter.format(h.currentPrice)}</td>
                    <td className="p-2">{currencyFormatter.format(h.currentValue)}</td>
                    <td className={`p-2 font-semibold ${h.gainLossAmount >= 0 ? "text-green-600" : "text-red-600"}`}>
                      {currencyFormatter.format(h.gainLossAmount)} ({percentFormatter(h.gainLossPercentage)})
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

      </div>
    </Layout>
  );
}
