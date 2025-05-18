"use client";
import { useState } from "react";

//Pagina de registro dle usuario usando react con envio del formulario al backend
export default function Page() {
  const [name, setName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      alert("Las contraseñas no coinciden.");
      return;
    }

    //Estructura del dto para envio del json para alpaca y los campos personalizados para el usuario
    const now = new Date().toISOString();

    const dto = {
      contact: {
        email_address: email,
        phone_number: phone,
        street_address: ["20 N San Mateo Dr"],
        city: "San Mateo",
        state: "CA",
        postal_code: "94401",
      },
      identity: {
        given_name: name,
        family_name: lastName,
        date_of_birth: "1970-01-01",
        country_of_citizenship: "USA",
        country_of_birth: "USA",
        party_type: "natural_person",
        tax_id: "444-55-4321",
        tax_id_type: "USA_SSN",
        country_of_tax_residence: "USA",
        funding_source: ["employment_income"],
      },
      disclosures: {
        is_control_person: false,
        is_affiliated_exchange_or_finra: false,
        is_affiliated_exchange_or_iiroc: false,
        is_politically_exposed: false,
        immediate_family_exposed: false,
        is_discretionary: false,
      },
      agreements: [
        {
          agreement: "customer_agreement",
          signed_at: now,
          ip_address: "127.0.0.1",
        },
      ],
      documents: [],
      trusted_contact: {
        given_name: "Jane",
        family_name: "Doe",
        email_address: "jane.doe@example.com",
      },
      minor_identity: null,
      entity_id: null,
      additional_information: "",
      account_type: "trading",
      account_sub_type: null,
      trading_type: "margin",
      auto_approve: null,
      beneficiaries: null,
      trading_configurations: null,
      currency: null,
      enabled_assets: ["us_equity"],
      instant: null,
      authorized_individuals: null,
      ultimate_beneficial_owners: null,
      sub_correspondent: null,
      primary_account_holder_id: null,

      //Estos son campos personalizados para el backend
      username,
      password
    };

    //Realizo petición post al backend y se maneja la respuesta
    try {
      const response = await fetch(`http://localhost:8080/api/usuario/v1/crear`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dto)
      });

      if (response.ok) {
        alert("Usuario registrado correctamente.");
      } else {
        const error = await response.text();
        alert("Error al registrar usuario: " + error);
      }
    } catch (error) {
      console.error(error);
      alert("Error no controlado por el usuario");
    }
  };

  return (
    <section className="relative h-screen flex">
  {/* Sección Izquierda - Fondo verde con logo */}
  <div className="w-1/2 flex flex-col items-center justify-center bg-[#1D5245] text-white p-12 shadow-xl">
    <img src="/traderbosque.png" alt="TraderBosque Logo" className="mb-6 w-32" />
    <h1 className="text-5xl font-extrabold drop-shadow-lg">TraderBosque</h1>
    <p className="text-lg mt-3 max-w-xl text-gray-300 text-center">
      Únete a nuestra comunidad y accede a herramientas avanzadas para mejorar tus operaciones.
    </p>
  </div>

  {/* Sección Derecha - Formulario de Registro */}
  <div className="w-1/2 flex flex-col items-center justify-center bg-white p-8 rounded-lg shadow-lg">
    <h2 className="text-3xl font-bold text-green-600 mb-6 text-center">Registro de Usuario</h2>

    <form onSubmit={handleSubmit} className="text-left space-y-4 w-full max-w-md">
      <div>
        <label className="block text-sm font-semibold text-gray-700">Nombre</label>
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} required className="w-full px-4 py-3 bg-gray-100 text-gray-900 border border-gray-300 rounded-lg focus:ring-green-400 shadow-sm" />
      </div>

      <div>
        <label className="block text-sm font-semibold text-gray-700">Apellido</label>
        <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} required className="w-full px-4 py-3 bg-gray-100 text-gray-900 border border-gray-300 rounded-lg focus:ring-green-400 shadow-sm" />
      </div>

      <div>
        <label className="block text-sm font-semibold text-gray-700">Correo Electrónico</label>
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required className="w-full px-4 py-3 bg-gray-100 text-gray-900 border border-gray-300 rounded-lg focus:ring-green-400 shadow-sm" />
      </div>

      <div>
        <label className="block text-sm font-semibold text-gray-700">Teléfono</label>
        <input type="text" value={phone} onChange={(e) => setPhone(e.target.value)} required className="w-full px-4 py-3 bg-gray-100 text-gray-900 border border-gray-300 rounded-lg focus:ring-green-400 shadow-sm" />
      </div>

      <div>
        <label className="block text-sm font-semibold text-gray-700">Nombre de Usuario</label>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} required className="w-full px-4 py-3 bg-gray-100 text-gray-900 border border-gray-300 rounded-lg focus:ring-green-400 shadow-sm" />
      </div>

      <div>
        <label className="block text-sm font-semibold text-gray-700">Contraseña</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required className="w-full px-4 py-3 bg-gray-100 text-gray-900 border border-gray-300 rounded-lg focus:ring-green-400 shadow-sm" />
      </div>

      <div>
        <label className="block text-sm font-semibold text-gray-700">Confirmar Contraseña</label>
        <input type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} required className="w-full px-4 py-3 bg-gray-100 text-gray-900 border border-gray-300 rounded-lg focus:ring-green-400 shadow-sm" />
      </div>

      <button type="submit" className="w-full px-6 py-3 bg-green-600 rounded-lg text-white font-semibold shadow-md transition hover:scale-105 text-center">
        Registrarse
      </button>
    </form>

    <div className="mt-4 text-gray-700 text-sm">
      ¿Ya tienes cuenta? <a href="/login" className="text-[#CB8A59] hover:underline">Inicia sesión aquí</a>
    </div>
  </div>
</section>
  );
}
