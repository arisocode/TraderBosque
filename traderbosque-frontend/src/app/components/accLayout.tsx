import { Children } from "react";
import { NavBar } from "./NavBar"
import { SideBarAcc } from "./SideBarAcc"

/*
Es muy importante tener estos layouts para mejorar el orden y facilidad de uso de cada elemento .
*/
export default function accLayout({ children,
}: {
    children: React.ReactNode
}) {

    return (
        <>

            <NavBar />
            <div className="layout">
                <SideBarAcc />
                <main className="main-content">{children}</main>
            </div>
        </>
    );
}
