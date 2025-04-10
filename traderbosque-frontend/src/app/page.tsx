

export default function Home() {
  return (
    <div>
      <section className="relative h-screen bg-cover bg-center flex items-center justify-center text-white text-center px-4" >
        <div className="bg-black bg-opacity-75 p-12 rounded-2xl shadow-2xl transform transition-all duration-300 hover:scale-105">
          <h1 className="text-5xl font-extrabold text-green-400 drop-shadow-lg">Traderbosque Plataforma</h1>
          <p className="text-lg mt-3 max-w-xl mx-auto">Domina el mercado con herramientas avanzadas y análisis en tiempo real.</p>
          <div className="mt-6 flex justify-center space-x-6">
            <a href="/login" className="px-6 py-3 bg-green-500 hover:bg-green-600 rounded-lg text-white font-semibold shadow-md transition-all">Iniciar Sesión</a>
            <a href="/register" className="px-6 py-3 bg-blue-500 hover:bg-blue-600 rounded-lg text-white font-semibold shadow-md transition-all">Registrarse</a>
          </div>
        </div>
      </section>


      <section className="py-20 text-center bg-gray-800">
        <h2 className="text-4xl font-bold text-green-400">¿Por qué elegir Traderbosque?</h2>
        <p className="text-lg mt-4 max-w-2xl mx-auto text-gray-300">Accede a herramientas avanzadas para maximizar tus inversiones, con análisis en tiempo real y seguridad garantizada.</p>

        <div className="mt-12 flex flex-wrap justify-center gap-8">
          <div className="bg-green-500 text-white p-8 rounded-lg shadow-xl w-80 transform hover:scale-105 transition-transform duration-300">
            <h3 className="text-2xl font-semibold">📊 Análisis en Tiempo Real</h3>
            <p className="mt-2">Gráficos detallados, alertas personalizadas y predicciones precisas.</p>
          </div>

          <div className="bg-blue-500 text-white p-8 rounded-lg shadow-xl w-80 transform hover:scale-105 transition-transform duration-300">
            <h3 className="text-2xl font-semibold">🔒 Seguridad de Alto Nivel</h3>
            <p className="mt-2">Protección con los más altos estándares de ciberseguridad.</p>
          </div>

          <div className="bg-yellow-500 text-white p-8 rounded-lg shadow-xl w-80 transform hover:scale-105 transition-transform duration-300">
            <h3 className="text-2xl font-semibold">📈 Estrategias Inteligentes</h3>
            <p className="mt-2">Automatiza tus inversiones y maximiza tus ganancias.</p>
          </div>
        </div>
      </section>

      <section className="py-20 bg-gray-900 text-center">
        <h2 className="text-4xl font-bold text-green-400">Tendencias del Mercado</h2>
        <p className="text-lg mt-4 max-w-2xl mx-auto text-gray-300">Observa la evolución del mercado con nuestros gráficos en tiempo real.</p>
        <div className="mt-12 max-w-4xl mx-auto p-6 bg-gray-800 rounded-xl shadow-xl">
          <canvas id="tradingChart"></canvas>
        </div>
      </section>
    </div>
  );
}
