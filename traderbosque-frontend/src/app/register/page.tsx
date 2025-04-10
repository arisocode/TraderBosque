export default function page() {
    return (
        <div className="h-screen flex items-center justify-center bg-cover bg-center">
            <div className="bg-black bg-opacity-80 p-10 rounded-xl shadow-xl w-full max-w-3xl text-white">
                <h1 className="text-4xl font-bold text-green-500 text-center mb-6">Registro de Usuario</h1>

                <form action="/process-register" method="POST" className="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label className="block text-sm font-semibold">Nombre</label>
                        <input type="text" id="name" name="name" required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg focus:ring-2 focus:ring-green-400" />
                    </div>
                    <div>
                        <label className="block text-sm font-semibold">Apellido</label>
                        <input type="text" id="lastName" name="lastName" required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg focus:ring-2 focus:ring-green-400" />
                    </div>
                    <div>
                        <label className="block text-sm font-semibold">Correo Electrónico</label>
                        <input type="email" id="email" name="email" required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg focus:ring-2 focus:ring-green-400" />
                    </div>
                    <div>
                        <label className="block text-sm font-semibold">Nombre de Usuario</label>
                        <input type="text" id="username" name="username" required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg focus:ring-2 focus:ring-green-400" />
                    </div>
                    <div>
                        <label className="block text-sm font-semibold">Contraseña</label>
                        <input type="password" id="password" name="password" required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg focus:ring-2 focus:ring-green-400" />
                    </div>
                    <div>
                        <label className="block text-sm font-semibold">Confirmar Contraseña</label>
                        <input type="password" id="confirm-password" name="confirm-password" required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg focus:ring-2 focus:ring-green-400" />
                    </div>
                    <div className="col-span-1 md:col-span-2 flex flex-col items-center">
                        <button type="submit" className="w-full md:w-1/2 bg-green-500 hover:bg-green-600 text-white font-bold py-3 rounded-lg transition">Registrarse</button>
                        <p className="mt-4 text-sm text-gray-300">¿Ya tienes cuenta? <a href="/login" className="text-green-400 hover:underline">Inicia sesión aquí</a></p>
                    </div>
                </form>


                <div className="mt-8">
                    <canvas id="registerChart"></canvas>
                </div>
            </div>
        </div>
    );
}
