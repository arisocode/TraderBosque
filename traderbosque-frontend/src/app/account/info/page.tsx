import Form from 'next/form';
import Layout from "../../components/accLayout";
import { TableComponent } from "../../components/Table"
import { AccordionComponent } from "../../components/Accordion"
import PasswordChange from "../../components/PasswordChange"

//Este componente no realiza gran cosa mas que conseguir la información de un usuario especifico la idea es usando el almacenamiento local poder conseguir el username para mostrar todo acorde con el usuario ingresado
export default async function page() {

    const userData = await fetch('http://localhost:8080/api/usuario/v1/usuarios/1')
    const user = await userData.json()

    return (
        <>
            <Layout>
                <AccordionComponent personal={<TableComponent data={user} />} password={<PasswordChange />}></AccordionComponent>
            </Layout>
        </>
    );
}
