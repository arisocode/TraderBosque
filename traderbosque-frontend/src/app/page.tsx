export default function Home() {
  return (
    <div>
      <section className="relative h-screen flex flex-col items-center justify-center bg-[#1D5245] text-white p-12 shadow-xl">
      <img src="/traderbosque.png" alt="TraderBosque Logo" className="mb-6 w-32" />
      <h1 className="text-5xl font-extrabold drop-shadow-lg">TraderBosque</h1>
      <p className="text-lg mt-3 max-w-xl text-gray-300 text-center">
        Domina el mercado con herramientas avanzadas y análisis en tiempo real.
      </p>

      <div className="mt-6 flex gap-4">
        <a href="/login" className="px-6 py-3 bg-[#CB8A59] rounded-lg text-white font-semibold shadow-md transition hover:scale-105 text-center">
          Iniciar Sesión
        </a>
        <a href="/register" className="px-6 py-3 bg-[#CB8A59] rounded-lg text-white font-semibold shadow-md transition hover:scale-105 text-center">
          Registrarse
        </a>
      </div>
    </section>

      <section className="py-20 text-center bg-white">
        <h2 className="text-4xl font-bold text-green-700">¿Por qué elegir TraderBosque?</h2>
        <p className="text-lg mt-4 max-w-2xl mx-auto text-gray-600">
          Accede a herramientas avanzadas para maximizar tus inversiones, con análisis en tiempo real y seguridad garantizada.
        </p>

        <div className="mt-12 flex flex-wrap justify-center gap-8">
          <div className="bg-pink-600 text-white p-8 rounded-lg shadow-xl w-80 transform hover:scale-105 transition-transform duration-300">
            <h3 className="text-2xl font-semibold">📊 Análisis en Tiempo Real</h3>
            <p className="mt-2">Gráficos detallados, alertas personalizadas y predicciones precisas.</p>
          </div>

          <div className="bg-yellow-400 text-white p-8 rounded-lg shadow-xl w-80 transform hover:scale-105 transition-transform duration-300">
            <h3 className="text-2xl font-semibold">🔒 Seguridad de Alto Nivel</h3>
            <p className="mt-2">Protección con los más altos estándares de ciberseguridad.</p>
          </div>

          <div className="bg-purple-600 text-white p-8 rounded-lg shadow-xl w-80 transform hover:scale-105 transition-transform duration-300">
            <h3 className="text-2xl font-semibold">📈 Estrategias Inteligentes</h3>
            <p className="mt-2">Automatiza tus inversiones y maximiza tus ganancias.</p>
          </div>
        </div>
      </section>

      <section className="py-20 bg-[#1D5245] text-center">
        <h2 className="text-4xl font-bold text-white">Tendencias del Mercado</h2>
        <p className="text-lg mt-4 max-w-2xl mx-auto text-gray-200">
          Observa la evolución del mercado con nuestros gráficos en tiempo real.
        </p>
        <div className="mt-12 max-w-4xl mx-auto p-6 bg-white rounded-xl shadow-xl">
          <canvas id="tradingChart"></canvas>
        </div>
      </section>
    </div>
  );
}
