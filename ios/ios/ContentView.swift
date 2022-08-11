//
//  ContentView.swift
//  PogodexCollector
//
//  Created by Niels Marsman on 30/07/2022.
//

import SwiftUI
import PogodexLibrary

struct ContentView: View {
    
    private var viewModel = ViewModelProvider().counter
    @State var state: CounterViewModel.State = CounterViewModel.State(counter: 0)
    
    @State var text = "Waiting..."
    @State var counter = 0
    
    var body: some View {
        VStack {
            Text(text)
                .padding()
                .onAppear {
                    Task {
                        do {
                            text = try await GreetingHelper().greet()
                        } catch {
                            text = error.localizedDescription
                        }
                    }
                }
            
            Text(String(state.counter))
                .padding()
            
            Button("Reset", action: {
                Task {
                    viewModel.actions.trySend(element: CounterViewModel.ActionReset())
                }
            })
        }
        .onAppear {
            Task {
                try await viewModel.state.watch { state in
                    guard let state = state else { return }
                    self.state = state
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
