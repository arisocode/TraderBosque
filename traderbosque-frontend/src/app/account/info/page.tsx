"use client";

import { useEffect, useState } from "react";
import Layout from "../../components/accLayout";
import { TableComponent } from "../../components/Table";
import { AccordionComponent } from "../../components/Accordion";
import PasswordChange from "../../components/PasswordChange";

export default function AccountPage() {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const username = localStorage.getItem("user");

        if (username) {
            fetch(`http://localhost:8080/api/usuario/v1/usuarios/byusername/${username}`)
                .then((res) => res.json())
                .then((data) => setUser(data))
                .catch((err) => console.error("Error al obtener el usuario:", err));
        }
    }, []);

    if (!user) return <p>Cargando datos del usuario...</p>;

    return (
        <Layout>
            <AccordionComponent
                personal={<TableComponent data={user} />}
                password={<PasswordChange />}
            />
        </Layout>
    );
}
