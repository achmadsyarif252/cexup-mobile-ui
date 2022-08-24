package com.cexup.datum

import com.trian.component.R
import com.trian.domain.models.bean.MedicalRecordType
import com.trian.domain.models.model.MedicalRecordModel


val listMedicalRecord = listOf(
    MedicalRecordModel(
        "Rontgen",
        R.drawable.rontgen,
        MedicalRecordType.RONTGEN
    ),
    MedicalRecordModel(
        "Labtest",
        R.drawable.labtest,
        MedicalRecordType.LABTEST
    ),
    MedicalRecordModel(
        "Medicine",
        R.drawable.medicine,
        MedicalRecordType.MEDICINE
    ),
    MedicalRecordModel(
        "Note",
        R.drawable.note,
        MedicalRecordType.NOTE
    ),
)