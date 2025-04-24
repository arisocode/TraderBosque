
import { Accordion, AccordionContent, AccordionPanel, AccordionTitle } from "flowbite-react";

type props = {
    personal: React.ReactNode,
    password: React.ReactNode
}

//Un componente obtenido de FlowBite disponible en: https://flowbite-react.com/docs/components/accordion, ajustado a permitir ingresar otros componentes funcionales dentro
export function AccordionComponent({ personal, password }: props) {
    return (
        <Accordion>
            <AccordionPanel>
                <AccordionTitle>Información personal</AccordionTitle>
                <AccordionContent>
                    {personal}
                </AccordionContent>
            </AccordionPanel>
            <AccordionPanel>
                <AccordionTitle>Cambiar contraseña</AccordionTitle>
                <AccordionContent>
                    {password}
                </AccordionContent>
            </AccordionPanel>
        </Accordion>
    );
}