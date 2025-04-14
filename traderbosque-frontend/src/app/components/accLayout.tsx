import { NavBar } from "./NavBar"
import { SideBarAcc } from "./SideBarAcc"

/*
Es muy importante tener estos layouts para mejorar el orden y facilidad de uso de cada elemento.
*/
export default function accLayout() {
    return (
        <>
            <NavBar />
            <SideBarAcc />
        </>
    );
}
