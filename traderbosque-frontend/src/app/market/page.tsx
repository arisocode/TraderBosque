export default function page() {
    return (
        <div className="bg-gray-900 text-white">
            <div className="container mx-auto p-6">
                <h2 className="text-3xl font-bold text-center mb-6">📈 Trading en Tiempo Real</h2>

                <div className="flex justify-center mb-4">
                    <input id="symbolInput" type="text" placeholder="Ingrese símbolo (Ej: AAPL)"
                        className="px-4 py-2 rounded-lg text-black focus:outline-none" />
                    <button className="ml-2 px-4 py-2 bg-blue-600 hover:bg-blue-700 rounded-lg text-white">
                        Buscar
                    </button>
                </div>

                <div className="overflow-x-auto bg-gray-800 p-6 rounded-lg shadow-lg">
                    <div className="w-[2500px] h-[500px]">
                        <canvas id="tradingChart"></canvas>
                    </div>
                </div>

                <div className="flex justify-center space-x-6 mt-6">
                    <button className="px-8 py-4 text-xl bg-green-600 hover:bg-green-700 rounded-lg shadow text-white font-bold">
                        Comprar Acción
                    </button>
                    <button className="px-8 py-4 text-xl bg-red-600 hover:bg-red-700 rounded-lg shadow text-white font-bold">
                        Vender Acción
                    </button>
                </div>
            </div>
        </div>
    );
}
