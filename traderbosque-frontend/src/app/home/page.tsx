import "./home.css";
import Layout from "../components/accLayout";

export default function page() {
    return (
        <Layout>
            <div className="container">

                <div className="navbar">
                    <a href="/">Inicio</a>
                    <a href="/logout" className="logout">Cerrar Sesión</a>
                </div>


                <h1>Bienvenido a Traderbosque</h1>


                <div className="menu-items">
                    <div className="menu-card">
                        <i className="fas fa-chart-line"></i>
                        <h3>Ver Mercado</h3>
                        <p>Accede a las últimas tendencias del mercado en tiempo real.</p>
                        <a href="/market">Ver Mercado</a>
                    </div>
                    <div className="menu-card">
                        <i className="fas fa-briefcase"></i>
                        <h3>Mi Portafolio</h3>
                        <p>Visualiza y gestiona tu portafolio de inversiones.</p>
                        <a href="/trader/portfolio">Ver Portafolio</a>
                    </div>
                    <div className="menu-card">
                        <i className="fas fa-user"></i>
                        <h3>Mi Cuenta</h3>
                        <p>Revisa tu información personal y financiera.</p>
                        <a href="/trader/account">Ver Cuenta</a>
                    </div>
                </div>


                <a href="/logout" className="logout">Cerrar Sesión</a>
            </div>
        </Layout>
    );
}
