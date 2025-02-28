package com.example.playermonitoringapp.API

data class PredictionRequest(
    val Sex: Int,
    val MaritalStatus: Int,
    val Age: Int,
    val Salary: Double,
    val AdditionalIncome: Int,
    val HouseOwnership: Int,
    val HomeTelephone: Int,
    val EducationLevel: Int,
    val LoansOtherBanks: Int,
    val CorporateGuarantee: Int,
    val Company_01: Boolean,
    val Company_10: Boolean
)
