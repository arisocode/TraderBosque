"use client";
import { ErrorToast } from "../components/Toast";
import { useState, useEffect, useRef } from "react";


//Pagina de inicio de sesion
export default function Page() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showErrorToastKey, setShowErrorToastKey] = useState(0);
  const hasShown = useRef(false);

  useEffect(() => {
    if (!hasShown.current) {
      hasShown.current = true;
    }
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

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
        window.location.href = "/home";
      } else {
        setShowErrorToastKey(prev => prev + 1);
      }
    } catch (error) {
      alert("Error en la conexión con el servidor");
      console.error(error);
    }
  };

  return (
    <section className="relative h-screen flex">
      <div className="w-1/2 flex flex-col items-center justify-center bg-[#1D5245] text-white p-12 shadow-xl">
        <img src="/traderbosque.png" alt="TraderBosque Logo" className="mb-6 w-32" />
        <h1 className="text-5xl font-extrabold drop-shadow-lg">TraderBosque</h1>
        <p className="text-lg mt-3 max-w-xl text-gray-300 text-center">
          Domina el mercado con herramientas avanzadas y análisis en tiempo real.
        </p>
      </div>

      <div className="w-1/2 flex flex-col items-center justify-center bg-white p-8 rounded-lg shadow-lg">
        {showErrorToastKey >= 1 && <ErrorToast key={showErrorToastKey} message="Usuario o contraseña incorrectos" />
        }
        <h2 className="text-3xl font-bold text-green-600 mb-6 text-center">Accede a tu cuenta</h2>

        <form onSubmit={handleSubmit} className="text-left space-y-4 w-full max-w-md">
          <div>
            <label className="block text-sm font-semibold text-gray-700">Usuario</label>
            <input
              type="text"
              id="username"
              name="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              className="w-full px-4 py-3 mt-1 bg-gray-100 text-gray-900 border border-gray-300 rounded-lg focus:ring-green-400 shadow-sm"
            />
          </div>

          <div>
            <label className="block text-sm font-semibold text-gray-700">Contraseña</label>
            <input
              type="password"
              id="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="w-full px-4 py-3 mt-1 bg-gray-100 text-gray-900 border border-gray-300 rounded-lg focus:ring-green-400 shadow-sm"
            />
          </div>

          <button type="submit" className="w-full px-6 py-3 bg-green-600 rounded-lg text-white font-semibold shadow-md transition hover:scale-105 text-center">
            Iniciar sesión
          </button>
        </form>

        <div className="mt-4 text-gray-700 text-sm">
          ¿No tienes cuenta? <a href="/register" className="text-[#CB8A59] hover:underline">Regístrate aquí</a>
        </div>
      </div>
    </section>
  );
}