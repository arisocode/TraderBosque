"use client";
import "./home.css";
import Layout from "../components/accLayout";
import { SuccessToast } from "../components/Toast";
import { useEffect, useRef, useState } from "react";

export default function Page() {
    const hasShown = useRef(false);

    useEffect(() => {
        if (!hasShown.current) {
            hasShown.current = true;
        }
    }, []);

    return (
        <Layout>
            <SuccessToast message="¡Bienvenido de vuelta!" />

            <div className="container">
                <div className="navbar">
                    <a href="/">Inicio</a>
                    <a href="/logout" className="logout">Cerrar Sesión</a>
                </div>

                <h1>Bienvenido a Traderbosque</h1>


                <h1 className="text-5xl font-extrabold text-white drop-shadow-lg mb-4">Bienvenido a TraderBosque</h1>

                <div className="menu-items">
                    <div className="menu-card">
                        <i className="fas fa-chart-line"></i>
                        <a href="/market">
                        <h3>Ver Mercado</h3>
                        <p>Accede a las últimas tendencias del mercado en tiempo real.</p>
                        </a>
                    </div>
                    <div className="menu-card">
                        <i className="fas fa-briefcase"></i>
                        <a href="/portfolio">
                        <h3>Mi Portafolio</h3>
                        <p>Visualiza y gestiona tu portafolio de inversiones.</p>
                        </a>
                    </div>
                    <div className="menu-card">
                        <i className="fas fa-user"></i>
                        <a href="/trader/account">
                        <h3>Mi Cuenta</h3>
                        <p>Revisa tu información personal y financiera.</p>
                        </a>
                    </div>
                </div>

                <a href="/logout" className="logout">Cerrar Sesión</a>
            </div>
        </Layout>
    );
}
