import { useEffect, useState } from "react";
import PrimaryScroller from "./PrimaryScroller";
import SecondaryScroller from "./SecondaryScroller";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';


function Scrollers() {

    const [hospitals, setHospitals] = useState([])
    const [patients, setPatients] = useState([])

    const urlHospitals = 'http://localhost:8181/api/organization/v1/'

    useEffect(() => {
        const fetchHospitals = async (url) => {
          
            const response = await fetch(url)
            const data = await response.json()

            setHospitals(data)
        }

        fetchHospitals(urlHospitals)
            .catch(console.error)
        
    }, [])

    function handleClickHospital(event, hospitalsItems) {
        const hospitalId = event.target.id
        console.log(hospitalId)

        hospitalsItems.map((hospital, _) => {
            if(hospital.id === hospitalId) {
                setPatients(hospital.patients)
            }
        })

    }

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [patient, setPatient] = useState({
        "resourceType": "Patient",
        "id": "patient-0",
        "name": [
          {
            "use": "official",
            "family": "SmileTest",
            "given": ["GregTest"]
          }
        ],
        "gender": "male",
        "birthDate": "1985-02-15",
        "address": [
          {
            "use": "home",
            "line": ["456 Oak Street"],
            "city": "Los Angeles",
            "state": "CA",
            "postalCode": "90001",
            "country": "USA"
          }
        ]
      })

    function handleClickPatient(event, patientsItems) {
        const patientId = event.target.id
        
        patientsItems.map((patient, index) => {
            if(patient.id === patientId) {
                setPatient(patients[index])
            }
        })
        // MODAL SHOW
        handleShow()
    }

    

    if(!hospitals.length) {
        <div>
            <p>Loading...</p>
        </div>
    }

    return ( 
        <div>
            <div className="scrollers">
                <div className="scrollers-title" onClick={(event) => handleClickHospital(event, hospitals)}>
                    <h3>Hospitals</h3>
                    <PrimaryScroller hospitals={hospitals}></PrimaryScroller>
                </div>
                <div className="scrollers-title" onClick={(event) => handleClickPatient(event, patients)}>
                    <h3>Patients</h3>
                    <SecondaryScroller data={patients}></SecondaryScroller>
                </div>
            </div>
            <div>
                <Modal show={show} onHide={handleClose}>
                    <Modal.Header>
                    <Modal.Title>{patient.name[0].given[0] + ` ` + patient.name[0].family}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div>
                            <p>
                                Date of Birth: {patient.birthDate}
                            </p>
                            <p>
                                City: {patient.address[0].city + ` ` + 
                                    patient.address[0].state}
                            </p>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    </Modal.Footer>
                </Modal>
            </div>
            {/* <p>{JSON.stringify(hospitals)}</p> */}
            <br></br>
            {/* <p>{JSON.stringify(patients)}</p> */}
            <p>{JSON.stringify(patient)}</p>
        </div>
     );
}

export default Scrollers;