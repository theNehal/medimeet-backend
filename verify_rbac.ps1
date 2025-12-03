$baseUrl = "http://localhost:8080"

# 1. Login as Patient
Write-Host "1. Logging in as Patient..."
$bodyPatient = @{ email = "test@example.com"; password = "password" } | ConvertTo-Json
$resPatient = Invoke-RestMethod -Uri "$baseUrl/auth/login" -Method Post -Body $bodyPatient -ContentType "application/json"
$tokenPatient = $resPatient.token
Write-Host "Patient Token: $tokenPatient"

# 2. Try to access Admin API as Patient (Should Fail)
Write-Host "`n2. Testing Patient access to Admin API (Should Fail)..."
try {
    Invoke-RestMethod -Uri "$baseUrl/api/admins" -Method Get -Headers @{ Authorization = "Bearer $tokenPatient" }
    Write-Host "ERROR: Patient was able to access Admin API!" -ForegroundColor Red
} catch {
    Write-Host "Success: Patient denied access. Status: $($_.Exception.Response.StatusCode.value__)"
}

# 3. Try to access Patient API as Patient (Should Succeed)
Write-Host "`n3. Testing Patient access to Patient API (Should Succeed)..."
try {
    Invoke-RestMethod -Uri "$baseUrl/api/patients" -Method Get -Headers @{ Authorization = "Bearer $tokenPatient" }
    Write-Host "Success: Patient accessed Patient API."
} catch {
    Write-Host "ERROR: Patient failed to access Patient API: $($_.Exception.Message)" -ForegroundColor Red
}

# 4. Login as Admin
Write-Host "`n4. Logging in as Admin..."
$bodyAdmin = @{ email = "admin@example.com"; password = "password" } | ConvertTo-Json
$resAdmin = Invoke-RestMethod -Uri "$baseUrl/auth/login" -Method Post -Body $bodyAdmin -ContentType "application/json"
$tokenAdmin = $resAdmin.token
Write-Host "Admin Token: $tokenAdmin"

# 5. Try to access Admin API as Admin (Should Succeed)
Write-Host "`n5. Testing Admin access to Admin API (Should Succeed)..."
try {
    Invoke-RestMethod -Uri "$baseUrl/api/admins" -Method Get -Headers @{ Authorization = "Bearer $tokenAdmin" }
    Write-Host "Success: Admin accessed Admin API."
} catch {
    Write-Host "ERROR: Admin failed to access Admin API: $($_.Exception.Message)" -ForegroundColor Red
}
