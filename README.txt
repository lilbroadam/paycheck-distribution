Profile - different users. Contains vaults
Vault - contains accounts. Vaults can have different configurations of accounts
Account - a bank account (checking, saving, etc). Has a name, and an AbsorbtionQuota
AbsorbtionQuota - contains either or both a flat rate and a percent rate of an account

DistributionManager - distributes a paycheck among a vault using the AbsorbtionQuota of accounts
Paycheck - contains all information related to an individual paycheck (pay ammount, pay period, taxes, perc taxes, date, company, other notes)
Historian - maintains a history log of paychecks and how they were distributed