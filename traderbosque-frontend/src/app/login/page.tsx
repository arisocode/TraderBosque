export default function page() {
    return (
        <div className="h-screen flex items-center justify-center bg-cover bg-center">
            <div className="bg-white bg-opacity-10 backdrop-blur-lg p-8 rounded-2xl shadow-2xl max-w-md w-full text-white text-center border border-gray-300 border-opacity-30">
                <h1 className="text-5xl font-extrabold text-green-400 drop-shadow-lg mb-4">Traderbosque</h1>
                <p className="text-gray-300 text-lg mb-6">Accede a tu cuenta para operar con confianza.</p>

                <form action="/login" method="POST" className="text-left space-y-4" />
                <div>
                    <label className="block text-sm font-semibold text-gray-300">Usuario</label>
                    <input type="text" id="username" name="username" required
                        className="w-full px-4 py-3 mt-1 bg-gray-800 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400 shadow-sm" />
                </div>

                <div>
                    <label className="block text-sm font-semibold text-gray-300">Contraseña</label>
                    <input type="password" id="password" name="password" required
                        className="w-full px-4 py-3 mt-1 bg-gray-800 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400 shadow-sm" />
                </div>

                <button className="w-full bg-green-500 hover:bg-green-600 text-white py-3 rounded-lg font-semibold transition transform hover:scale-105 shadow-md">
                    <a href="/home" className="text-green-400 hover:underline">Iniciar sesion</a>
                </button>


                <div className="mt-4 text-gray-300 text-sm">
                    ¿No tienes cuenta? <a href="/register" className="text-green-400 hover:underline">Regístrate aquí</a>
                </div>
            </div>
        </div>
    );
}
