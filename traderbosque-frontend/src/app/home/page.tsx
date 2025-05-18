import "./home.css";
import Layout from "../components/accLayout";

export default function page() {
    return (
        <Layout>
            <div className="container">

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
            </div>
        </Layout>
    );
}
