"use client";

import "./portfolio.css";
import Layout from "../components/accLayout";

import { useState } from "react";
import { Bar } from "react-chartjs-2";
import { Chart, BarElement, CategoryScale, LinearScale } from "chart.js";

Chart.register(BarElement, CategoryScale, LinearScale);

export default function InvestmentDashboard() {
  const [investments, setInvestments] = useState([
    { id: 1, name: "Inversión A", amount: 5000, returns: 10 },
    { id: 2, name: "Inversión B", amount: 3000, returns: -5 },
    { id: 3, name: "Inversión C", amount: 2000, returns: 7 },
  ]);

  const [newInvestment, setNewInvestment] = useState({ name: "", amount: 0, returns: 0 });

  const addInvestment = () => {
    setInvestments([...investments, { ...newInvestment, id: investments.length + 1 }]);
  };

  const removeInvestment = (id) => {
    setInvestments(investments.filter((inv) => inv.id !== id));
  };

  const chartData = {
    labels: investments.map((inv) => inv.name),
    datasets: [
      {
        label: "Rendimiento (%)",
        data: investments.map((inv) => inv.returns),
        backgroundColor: "rgba(75, 192, 192, 0.6)",
      },
    ],
  };

  return (

    <Layout>
    <div className="bg-gray-900 container mx-auto p-6">
      <nav className="bg-gray-800 text-white p-4">
        <h1 className="text-xl font-bold">Gestión de Inversiones</h1>
      </nav>

      <div className="bg-white p-6 rounded-md my-4">
        <h2 className="text-lg font-semibold">Rendimiento de Inversiones</h2>
        <Bar data={chartData} />
      </div>

      <div className="bg-gray-100 p-6 rounded-md my-4">
        <h2 className="text-lg font-semibold">Tus Inversiones</h2>
        <ul>
          {investments.map((inv) => (
            <li key={inv.id} className="border-b py-2 flex justify-between">
              {inv.name}: ${inv.amount} - {inv.returns}%
              <button onClick={() => removeInvestment(inv.id)} className="bg-red-500 text-white px-2 py-1 rounded">
                ❌ Eliminar
              </button>
            </li>
          ))}
        </ul>
      </div>

      <div className="bg-gray-100 p-6 rounded-md my-4">
        <h2 className="text-lg font-semibold">Agregar Nueva Inversión</h2>
        <input type="text" placeholder="Nombre" onChange={(e) => setNewInvestment({ ...newInvestment, name: e.target.value })} className="border p-2 rounded" />
        <input type="number" placeholder="Monto" onChange={(e) => setNewInvestment({ ...newInvestment, amount: parseFloat(e.target.value) })} className="border p-2 rounded ml-2" />
        <input type="number" placeholder="Rendimiento (%)" onChange={(e) => setNewInvestment({ ...newInvestment, returns: parseFloat(e.target.value) })} className="border p-2 rounded ml-2" />
        <button onClick={addInvestment} className="bg-green-500 text-white px-4 py-2 rounded ml-2">➕ Agregar</button>
      </div>
    </div>
    </Layout>
  );
}