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
    <div className="h-screen flex items-center justify-center bg-cover bg-center">
      <div className="bg-black bg-opacity-80 p-10 rounded-xl shadow-xl w-full max-w-3xl text-white">
        <h1 className="text-4xl font-bold text-green-500 text-center mb-6">Registro de Usuario</h1>

        <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <label className="block text-sm font-semibold">Nombre</label>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg" />
          </div>
          <div>
            <label className="block text-sm font-semibold">Apellido</label>
            <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg" />
          </div>
          <div>
            <label className="block text-sm font-semibold">Correo Electrónico</label>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg" />
          </div>
          <div>
            <label className="block text-sm font-semibold">Teléfono</label>
            <input type="text" value={phone} onChange={(e) => setPhone(e.target.value)} required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg" />
          </div>
          <div>
            <label className="block text-sm font-semibold">Nombre de Usuario</label>
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg" />
          </div>
          <div>
            <label className="block text-sm font-semibold">Contraseña</label>
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg" />
          </div>
          <div>
            <label className="block text-sm font-semibold">Confirmar Contraseña</label>
            <input type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} required className="w-full px-4 py-2 bg-gray-800 text-white rounded-lg" />
          </div>
          <div className="col-span-1 md:col-span-2 flex flex-col items-center">
            <button type="submit" className="w-full md:w-1/2 bg-green-500 hover:bg-green-600 text-white font-bold py-3 rounded-lg transition">Registrarse</button>
            <p className="mt-4 text-sm text-gray-300">
              ¿Ya tienes cuenta? <a href="/login" className="text-green-400 hover:underline">Inicia sesión aquí</a>
            </p>
          </div>
        </form>
      </div>
    </div>
  );
}
