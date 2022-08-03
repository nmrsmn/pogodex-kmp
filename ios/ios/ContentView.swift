//
//  ContentView.swift
//  PogodexCollector
//
//  Created by Niels Marsman on 30/07/2022.
//

import SwiftUI
import PogodexLibrary

struct ContentView: View {
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
            Text("\(counter)")
                .padding()
        }
        .onAppear {
            Task {
                do {
                    try await CounterServiceHelper().service.counter.collect(collector: CounterCollector { count in
                        self.counter = count
                    })
                } catch {
                    text = error.localizedDescription
                }
            }
        }
    }
    
    class CounterCollector : Kotlinx_coroutines_coreFlowCollector {
        let onEmit: (Int) -> Void
        
        init(_ onEmit: @escaping (Int) -> Void) {
            self.onEmit = onEmit
        }
        
        func emit(value: Any?) async throws {
            if let value = value as? Int {
                onEmit(value)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
