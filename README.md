# Dr Pepper Bottle Plant
This is a project for the "Programmering med Java" course (DEV26M). 

The project simulates a Dr Pepper Bottling Plant in java.

## Basic Flow
This chart shows how a bottle gets filled with Dr Pepper in the plant.

```mermaid
flowchart TD
  A[Unscrambler] --> B[Rinser]
  B --> C[Dr Pepper Filler]
  C --> D{Labeling and Quality Control}
  D -->|Good| Y[Case Packer]
  Y --> Z[Pallet]
  D -->|Bad| X[Rejected]
```
