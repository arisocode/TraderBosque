"use client";

import { useState } from "react";

//Pagina de inicio de sesion
export default function Page() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e: React.FormEvent) => { //Manejamos el envio del formulario
    e.preventDefault();

    //Peticion al post y manejo de respuesta por pate del backend
    try {
      const response = await fetch(`http://localhost:8080/api/usuario/v1/checklogin`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ userName: username, password: password }),
      });

      if (response.ok) {
        const data = await response.json();
        console.log("✅ Login exitoso", data);
        localStorage.setItem("user", username);
        console.log(localStorage.getItem("user"));
        window.location.href = "/home";
      } else {
        const error = await response.text();
        alert("Usuario o contraseña incorrectos: " + error);
      }
    } catch (error) {
      alert("Error en la conexión con el servidor");
      console.error(error);
    }
  };

  return (
    <div className="h-screen flex items-center justify-center bg-cover bg-center">
      <div className="bg-gray-800 text-white p-4 backdrop-blur-lg p-8 rounded-2xl shadow-2xl max-w-md w-full text-white text-center border border-gray-300 border-opacity-30">
        <h1 className="text-5xl font-extrabold text-green-400 drop-shadow-lg mb-4">TraderBosque</h1>
        <p className="text-gray-300 text-lg mb-6">Accede a tu cuenta para operar con confianza.</p>

        <form onSubmit={handleSubmit} className="text-left space-y-4">
          <div>
            <label className="block text-sm font-semibold text-gray-300">Usuario</label>
            <input
              type="text"
              id="username"
              name="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              className="w-full px-4 py-3 mt-1 bg-gray-800 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400 shadow-sm"
            />
          </div>

          <div>
            <label className="block text-sm font-semibold text-gray-300">Contraseña</label>
            <input
              type="password"
              id="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="w-full px-4 py-3 mt-1 bg-gray-800 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400 shadow-sm"
            />
          </div>

          <button type="submit" className="w-full bg-green-500 hover:bg-green-600 text-white py-3 rounded-lg font-semibold transition transform hover:scale-105 shadow-md">
            Iniciar sesión
          </button>
        </form>

        <div className="mt-4 text-gray-300 text-sm">
          ¿No tienes cuenta? <a href="/register" className="text-green-400 hover:underline">Regístrate aquí</a>
        </div>
      </div>
    </div>
  );
}
