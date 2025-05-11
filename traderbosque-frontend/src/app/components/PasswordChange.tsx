'use client'
import { FormEvent, useState, useEffect } from "react";

//Este componente es para el cambio de contraseña
export default function PasswordChange() {
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');
    const username = localStorage.getItem("user")
    const [formData, setFormData] = useState({
        username: username,
        oldPassword: '',
        newPassword: ''
    });


    //Para tener presentes los cambios de los inputs
    const handleChange = (e: { target: { name: any; value: any; }; }) => {
        setFormData(prev => ({
            ...prev,
            [e.target.name]: e.target.value
        }));
    };

    //Para cuando se completa el formulario
    async function onSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        if (formData.newPassword !== confirmPassword) {
            setError('Las contraseñas no coinciden');
            alert('¡Las contraseñas no coinciden!');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/usuario/v1/updatePassword`, {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                alert('Contraseña cambiada con éxito!');
            } else {
                const result = await response.text();
                console.error(result);
                alert('Error al cambiar la contraseña');
            }
        } catch (error) {
            console.error('Error de red:', error);
            alert('Error de red al cambiar la contraseña');
        }
    }

    return (
        <>
            <form onSubmit={onSubmit}>
                <div>
                    <label>Contraseña actual:</label>
                    <input
                        type="password"
                        name="oldPassword"
                        value={formData.oldPassword}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label>Contraseña nueva:</label>
                    <input
                        type="password"
                        name="newPassword"
                        value={formData.newPassword}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label>Confirmar nueva contraseña:</label>
                    <input
                        type="password"
                        value={confirmPassword}
                        onChange={e => setConfirmPassword(e.target.value)}
                    />
                </div>
                <button type="submit">Cambiar contraseña</button>
            </form>
        </>
    );
}
