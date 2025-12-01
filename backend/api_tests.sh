#!/bin/bash

# Base URL
BASE_URL="http://localhost:8080"

# --- Authentication ---
echo "--- Testing Authentication ---"
# Login
echo "Logging in..."
LOGIN_RESPONSE=$(curl -s -X POST "$BASE_URL/auth/login" \
  -H "Content-Type: application/json" \
  -d '{"email": "test@example.com", "password": "password"}')
echo "Login Response: $LOGIN_RESPONSE"

# Extract Token (Simple extraction, assumes valid JSON response)
TOKEN=$(echo $LOGIN_RESPONSE | grep -o '"token":"[^"]*' | cut -d'"' -f4)
echo "Token: $TOKEN"

# Protected Endpoint
echo "Accessing Protected Endpoint..."
curl -X GET "$BASE_URL/auth/test" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"


# --- Patients ---
echo "--- Testing Patients ---"
# Create Patient
echo "Creating Patient..."
curl -X POST "$BASE_URL/api/patients" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890",
    "gender": "Male",
    "date_of_birth": "1990-01-01",
    "password_hash": "password123"
  }'
echo -e "\n"

# Get All Patients
echo "Getting All Patients..."
curl -X GET "$BASE_URL/api/patients" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"

# Get Patient by ID (Assuming ID 1 exists or was just created)
echo "Getting Patient 1..."
curl -X GET "$BASE_URL/api/patients/1" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"

# Update Patient
echo "Updating Patient 1..."
curl -X PUT "$BASE_URL/api/patients/1" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "John Doe Updated",
    "email": "john.doe@example.com",
    "phone": "0987654321",
    "gender": "Male",
    "date_of_birth": "1990-01-01"
  }'
echo -e "\n"

# Delete Patient (Commented out to avoid deleting data during simple run)
# echo "Deleting Patient 1..."
# curl -X DELETE "$BASE_URL/api/patients/1" \
#   -H "Authorization: Bearer $TOKEN"
# echo -e "\n"


# --- Doctors ---
echo "--- Testing Doctors ---"
# Create Doctor
echo "Creating Doctor..."
curl -X POST "$BASE_URL/api/doctors" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "Dr. Smith",
    "email": "dr.smith@example.com",
    "specialization": "Cardiology",
    "available_days": "Mon,Wed,Fri",
    "password_hash": "doctorpass"
  }'
echo -e "\n"

# Get All Doctors
echo "Getting All Doctors..."
curl -X GET "$BASE_URL/api/doctors" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"

# Get Doctor by ID
echo "Getting Doctor 1..."
curl -X GET "$BASE_URL/api/doctors/1" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"

# Get Doctor by Specialization
echo "Getting Doctors by Specialization (Cardiology)..."
curl -X GET "$BASE_URL/api/doctors/specialization/Cardiology" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"


# --- Admins ---
echo "--- Testing Admins ---"
# Create Admin
echo "Creating Admin..."
curl -X POST "$BASE_URL/api/admins" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "Admin User",
    "email": "admin@example.com",
    "role": "ADMIN",
    "password_hash": "adminpass"
  }'
echo -e "\n"

# Get All Admins
echo "Getting All Admins..."
curl -X GET "$BASE_URL/api/admins" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"


# --- Appointments ---
echo "--- Testing Appointments ---"
# Book Appointment (Assumes Patient 1 and Doctor 1 exist)
echo "Booking Appointment..."
curl -X POST "$BASE_URL/api/appointments" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "patient": {"id": 1},
    "doctor": {"id": 1},
    "appointmentDate": "2023-12-25T10:00:00",
    "reason": "Regular Checkup",
    "status": "Pending"
  }'
echo -e "\n"

# Get Appointments by Patient
echo "Getting Appointments for Patient 1..."
curl -X GET "$BASE_URL/api/appointments/patient/1" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"

# Get Appointments by Doctor
echo "Getting Appointments for Doctor 1..."
curl -X GET "$BASE_URL/api/appointments/doctor/1" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"

# Update Appointment Status (Assuming ID 1)
echo "Updating Appointment 1 Status..."
curl -X PUT "$BASE_URL/api/appointments/1/status/Confirmed" \
  -H "Authorization: Bearer $TOKEN"
echo -e "\n"

# Delete Appointment
# echo "Deleting Appointment 1..."
# curl -X DELETE "$BASE_URL/api/appointments/1" \
#   -H "Authorization: Bearer $TOKEN"
# echo -e "\n"
